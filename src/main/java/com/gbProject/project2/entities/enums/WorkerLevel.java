package com.gbProject.project2.entities.enums;

public enum WorkerLevel {

    JUNIOR(1),
    MID_LEVEL(2),
    SENIOR(3);

    private int code;

    private WorkerLevel(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public static WorkerLevel valueOf(int code){
        for(WorkerLevel x: WorkerLevel.values()){
            if(x.getCode() == code){
                return x;
            }
        }

        throw new IllegalArgumentException("Invalid worker level code.");

    }
    
}
