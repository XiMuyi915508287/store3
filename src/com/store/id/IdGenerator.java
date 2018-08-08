/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.id;

import com.altratek.altraserver.extensions.db.DbManager;
import com.aobi.common.ApplicationLocal;
import com.store.db.IDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author chenjingjun
 */
public final class IdGenerator implements IDao{
    private static final String insertUserIdGen = "INSERT INTO IdGen (IdType, CurrentId) VALUES(?, ?)";
    private static final String selectUserIdGen = "SELECT IdType, CurrentId FROM IdGen";

    private static final String selectUserId = "SELECT IdType, CurrentId FROM IdGen WHERE IdType = ? FOR UPDATE";
    private static final String updateUserId = "UPDATE IdGen SET CurrentId = CurrentId + ? WHERE IdType = ?";

    private static final IdGenerator instance = new IdGenerator();
    
    public static IdGenerator getInstance() {
        return instance;
    }
    
    private HashMap<Integer, IdGen> idGenMap = new HashMap<>();

    private IdGenerator() {
    }
    
    @Override
    public String getName() {
        return "IdGen";
    }

    @Override
    public void reload() {
        ArrayList<IdGen> idGens = loadIdGens();
        HashMap<Integer, IdGen> map = new HashMap<>();
        for(IdGen idGen : idGens) {
            map.put(idGen.idType, idGen);
        }
        for(IdType type : IdType.values()) {
            if(map.containsKey(type.typeId)){
                continue;
            }
            IdGen idGen = new IdGen(type.typeId, type.initValue, type.initValue);
            insertIdGen(idGen);
            map.put(idGen.idType, idGen);
        }
        this.idGenMap = map;
    }
    
    public long generateId(IdType idType) {
        IdGen idGen = idGenMap.get(idType.typeId);
        synchronized (idGen) {
            if(!idGen.canOfferIdCount(1)){
                if(!fill(idGen, idType.step, 1)){
                    return -1;
                }
            }
            return idGen.incrementIdAndGet();
        }
    }

    public long[] generateIds(IdType idType, int count) {
        IdGen idGen = idGenMap.get(idType.typeId);
        synchronized (idGen) {
            if(!idGen.canOfferIdCount(count)){
                if(!fill(idGen, idType.step, count)){
                    return null;
                }
            }
            return idGen.incrementIdAndGet(count);
        }
    }

    private boolean fill(IdGen idGen, int step, int needCount) {
        int increment = (step + needCount - 1) / step * step; // 取step的整数倍，向上取整
        updateIdGen(idGen, increment);
        return idGen.canOfferIdCount(needCount);
    }

    private ArrayList<IdGen> loadIdGens() {
       return DbManager.getWorkDb().executeQuery_ObjectListEx(selectUserIdGen, IdGen.builder);
    }
    
    private void insertIdGen(IdGen idGen) {
        DbManager.getWorkDb().executeInsertCommandEx(insertUserIdGen, new Object[]{idGen.idType, idGen.getCurrentMax()});
    }

    private void updateIdGen(IdGen idGen, int inc) {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                conn = DbManager.getWorkDb().getConnection();
                conn.setAutoCommit(false);

                stmt = conn.prepareStatement(selectUserId);
                stmt.setInt(1, idGen.idType);
                rs = stmt.executeQuery();
                boolean hasNext = rs.next();

                long currentId = idGen.getCurrentId();
                if (hasNext) {
                    currentId = rs.getLong("CurrentId");
                    idGen.setCurrentId(currentId);
                    idGen.setCurrentMax(currentId);
                }

                stmt.close();
                if (hasNext) {
                    stmt = conn.prepareStatement(updateUserId);
                    stmt.setLong(1, inc);
                    stmt.setInt(2, idGen.idType);
                    stmt.execute();
                    idGen.setCurrentMax(currentId + inc);
                }
                conn.commit();
            } catch (Throwable t) {
                try {
                    conn.rollback();
                } catch (Throwable t1) {
                    ApplicationLocal.instance().error("-", t1);
                }
                idGen.setCurrentMax(idGen.getCurrentId());
                ApplicationLocal.instance().error("<UserIdGenDB> fill id info error :", t);
            } finally {
                DbManager.releaseDbResource(rs, stmt, conn);
            }
    }
}
