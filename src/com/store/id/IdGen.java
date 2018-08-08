/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.id;

import com.altratek.altraserver.extensions.db.ResultObjectBuilder;
import com.store.logger.StoreLogger;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author chenjingjun
 */
public class IdGen {
    protected final int idType;
    private long currentId;
    private long currentMax;

    protected IdGen(int idType, long currentId, long currentMax) {
        this.idType = idType;
        this.currentId = currentId;
        this.currentMax = currentMax;
    }


    protected boolean canOfferIdCount(long needCount){
        return (currentId + needCount) <= currentMax;
    }

    protected long incrementIdAndGet(){
        return ++currentId;
    }

    protected long[] incrementIdAndGet(int needCount){
        long[] array = new long[needCount];
        for (int index = 0; index < needCount; index++) {
            array[index] = ++currentId;
            if(array[index] == 2) {
                StoreLogger.error(String.format("MARKKKKKKKKKKKKKKK~~~~~~~:Generator Id Dumplication!!!!!!currentId=%d, currentMax=%d", currentId, currentMax));
            }
        }
        return array;
    }

    protected long getCurrentId() {
        return currentId;
    }

    protected long getCurrentMax(){
        return this.currentMax;
    }

    protected void setCurrentId(long currentId) {
        this.currentId = currentId;
    }

    protected void setCurrentMax(long currentMax) {
        this.currentMax = currentMax;
    }

    protected static final ResultObjectBuilder<IdGen> builder = new ResultObjectBuilder<IdGen>() {
        @Override
        public IdGen build(ResultSet rs) throws SQLException {
            int idType = rs.getInt("IdType");
            long currentId = rs.getLong("CurrentId");
            return new IdGen(idType, currentId, currentId);
        }
    };
}
