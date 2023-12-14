package com.hotelreservationapp.request;

public class ResponseObject {
    private int code;
    private String message;
    private Object data;
    private int totalPage;
    private int pageSize;
    private String keyword;

    public ResponseObject(int code, String message, Object data, int totalPage, int pageSize, String keyword) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.totalPage = totalPage;
        this.pageSize = pageSize;
        this.keyword = keyword;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
