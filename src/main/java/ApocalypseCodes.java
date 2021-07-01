package main.java;

public enum ApocalypseCodes {
	
	ZOMBIE(1), CREATURES(2), ZOMBIE_FINALPOSITION(3);
	
	int code;
	
	ApocalypseCodes(int code) {
        this.code = code;
    }

    public int getCode(){
        return this.code;
    }

}
