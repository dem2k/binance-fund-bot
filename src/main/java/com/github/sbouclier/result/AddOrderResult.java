package com.github.sbouclier.result;

import java.util.List;

public class AddOrderResult extends Result<AddOrderResult.AddOrder> {
    
    public static class AddOrder {

        public static class Description {
            public String order;
            public String close;

            @Override
            public String toString() {
                return "Description{" +
                        "order='" + order + '\'' +
                        ", close='" + close + '\'' +
                        '}';
            }
        }

        public Description descr;
        public List<String> txid;

        @Override
        public String toString() {
            return "AddOrder{" +
                    "descr=" + descr +
                    ", txid=" + txid +
                    '}';
        }
    }
}
