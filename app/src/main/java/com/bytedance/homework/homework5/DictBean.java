package com.bytedance.homework.homework5;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DictBean {

    private Web_trans web_trans;
    private String input;
    private String le;
    private String lang;
    public Web_trans getWeb_trans() {
        return web_trans;
    }

    public class Web_trans {
        @SerializedName("web-translation")
        private List<Web_translation> web_translation;
        public List<Web_translation> getWeb_translation() {
            return web_translation;
        }
    }

    public class Web_translation {
        private String key;
        private List<Trans> trans;
        public List<Trans> getTrans() {
            return trans;
        }
    }

    public class Trans {
        private String value;
        private int support;
        private String url;

        public String getValue() {
            return value;
        }

    }

    private Ec ec;

    public Ec getEc() {
        return ec;
    }

    public class Ec {

        private List<Word> word;
        public List<Word> getWord() {
            return word;
        }

    }

    public class Word {
        private List<Trs> trs;
        private String usphone;
        private String ukphone;

        public List<Trs> getTrs() {
            return trs;
        }

        public String getUsphone() {
            return usphone;
        }

        public String getUkphone() {
            return ukphone;
        }
    }

    public class Trs {
        private List<Tr> tr;

        public List<Tr> getTr() {
            return tr;
        }
    }

    public class Tr {
        private L l;

        public L getL() {
            return l;
        }
    }

    public class L {

        private List<String> i;

        public List<String> getI() {
            return i;
        }

    }
}