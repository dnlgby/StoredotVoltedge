package com.example.storedotvoltedge.models;

import com.example.storedotvoltedge.models.ApiEntry;

import java.util.List;

public class ApiResponse {
    private int count;
    private List<ApiEntry> entries;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ApiEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<ApiEntry> entries) {
        this.entries = entries;
    }
}