package com.suiyi.jpa.Utils;

public class EnumName {

    public enum AdminType {

        NORMAL_ADMIN(0,"普通管理员"),

        SUPER_ADMIN(1,"超级管理员"),;

        private int value;

        private String caption;

        public int getValue() {
            return value;
        }

        public String getCaption() {
            return caption;
        }

        AdminType(int value, String caption) {
            this.value = value;
            this.caption = caption;
        }

        public static AdminType getOf(Integer v) {
            if (v == null) {
                return null;
            }
            for (AdminType status : values()) {
                if (v == status.getValue()) {
                    return status;
                }
            }
            return null;
        }

        public static String getCaptionByValue(Integer v) {
            AdminType es = getOf(v);
            return es == null ? "" : es.getCaption();
        }
    }
    
    public enum AdminRight {

        ORDER_MANAGE(0,"订单管理"),

        Goods_manage(1,"商品管理"),
        
        ALL_MANAGE(2,"全部");

        private Integer value;

        private String caption;

        public Integer getValue() {
            return value;
        }

        public String getCaption() {
            return caption;
        }

        AdminRight(Integer value, String caption) {
            this.value = value;
            this.caption = caption;
        }

        public static AdminRight getOf(Integer v) {
            if (v == null) {
                return null;
            }
            for (AdminRight status : values()) {
                if (v == status.getValue()) {
                    return status;
                }
            }
            return null;
        }

        public static String getCaptionByValue(Integer v) {
        	AdminRight es = getOf(v);
            return es == null ? "" : es.getCaption();
        }
    }
    
    
    public enum GoodState {

        ON_SALE(0,"上架"),

        OFF_SALE(1,"下架");

        private Integer value;

        private String caption;

        public Integer getValue() {
            return value;
        }

        public String getCaption() {
            return caption;
        }

        GoodState(Integer value, String caption) {
            this.value = value;
            this.caption = caption;
        }

        public static GoodState getOf(Integer v) {
            if (v == null) {
                return null;
            }
            for (GoodState status : values()) {
                if (v == status.getValue()) {
                    return status;
                }
            }
            return null;
        }

        public static String getCaptionByValue(Integer v) {
        	GoodState es = getOf(v);
            return es == null ? "" : es.getCaption();
        }
    }
    
    public enum GoodTypeState {

        ON(0,"启用"),

        off(1,"禁用"),;

        private int value;

        private String caption;

        public int getValue() {
            return value;
        }

        public String getCaption() {
            return caption;
        }

        GoodTypeState(int value, String caption) {
            this.value = value;
            this.caption = caption;
        }

        public static GoodTypeState getOf(Integer v) {
            if (v == null) {
                return null;
            }
            for (GoodTypeState status : values()) {
                if (v == status.getValue()) {
                    return status;
                }
            }
            return null;
        }

        public static String getCaptionByValue(Integer v) {
        	GoodTypeState es = getOf(v);
            return es == null ? "" : es.getCaption();
        }
    }
    
    
}
