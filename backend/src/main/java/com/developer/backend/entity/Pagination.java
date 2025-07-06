package com.developer.backend.entity;

public class Pagination {
    String Action;
    String TypeOrd;
    String Param;
    int Page;
    int Offset;
    int Limit;

    public Pagination() {
    }

    

    public Pagination(String action, String typeOrd, String param, int page, int offset, int limit) {
        Action = action;
        TypeOrd = typeOrd;
        Param = param;
        Page = page;
        Offset = offset;
        Limit = limit;
    }



    public String getTypeOrd() {
        return TypeOrd;
    }

    public void setTypeOrd(String typeOrd) {
        TypeOrd = typeOrd;
    }

    public String getParam() {
        return Param;
    }

    public void setParam(String param) {
        Param = param;
    }

    public int getPage() {
        return Page;
    }

    public void setPage(int page) {
        Page = page;
    }

    public int getOffset() {
        return Offset;
    }

    public void setOffset(int offset) {
        Offset = offset;
    }

    public int getLimit() {
        return Limit;
    }

    public void setLimit(int limit) {
        Limit = limit;
    }



    public String getAction() {
        return Action;
    }



    public void setAction(String action) {
        Action = action;
    }

    
    

    
    
}
