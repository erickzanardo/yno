package org.eck;

import java.util.HashMap;
import java.util.Map;

import org.eck.exceptions.NoDataCollectedException;
import org.eck.exceptions.NoTableInformedException;

public class CollectedData {
    private String table;
    private Map<String, Object> data = new HashMap<>();

    public void table(String table) {
        this.table = table;
    }

    public String table() {
        return table;
    }

    public void data(String field, Object value) {
        data.put(field, value);
    }

    public Map<String, Object> data() {
        return data;
    }

    public void validate() {
        if(table == null) throw new NoTableInformedException();
        if(data.size() == 0) throw new NoDataCollectedException();
    }
}
