package com.AtlasLibrary;

public class ActionResponse {
    private final boolean actionSuccess;
    private final String responseMessage;

    public ActionResponse(boolean isSuccess, String message) {
        this.actionSuccess = isSuccess;
        this.responseMessage = message;
    }
    public boolean isActionSuccess(){
        return actionSuccess;
    }
    public String getResponseMessage(){
        return responseMessage;
    }
    public static ActionResponse getSuccessfulActionResponse(){
        return new ActionResponse(true,"Action successfully Completed");
    }
    public static ActionResponse getSuccessfulActionResponse(String message){
        return new ActionResponse(true,message);
    }
    public static ActionResponse getFailedActionResponse(){
        return new ActionResponse(false,"Action failed");
    }
    public static ActionResponse getFailedActionResponse(String message){
        return new ActionResponse(false,message);
    }
    public static ActionResponse getActionResponseFromBoolean(boolean isSuccess){
        if(isSuccess){
            return getSuccessfulActionResponse();
        }
        else{
            return getFailedActionResponse();
        }
    }
    
}