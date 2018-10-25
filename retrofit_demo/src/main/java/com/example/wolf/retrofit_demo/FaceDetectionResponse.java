package com.example.wolf.retrofit_demo;

import java.util.List;

public class FaceDetectionResponse {
    private int img_height, img_width;
    private String img_id;
    private String res_code;
    private List<Face> face;

    public int getImg_height() {
        return img_height;
    }

    public int getImg_width() {
        return img_width;
    }

    public String getImg_id() {
        return img_id;
    }

    public String getRes_code() {
        return res_code;
    }

    public List<Face> getFaces() {
        return face;
    }

    public class Face {
        private String face_id;
        private Attribute attribute;

        public String getFace_id() {
            return face_id;
        }

        public Attribute getAttribute() {
            return attribute;
        }

        public class Attribute {
            private int age;
            private String gender;
            private int lefteye_opendegree, righteye_opendegree;
            private int mouth_opendegree;

            public int getAge() {
                return age;
            }

            public String getGender() {
                return gender;
            }

            public int getLefteye_opendegree() {
                return lefteye_opendegree;
            }

            public int getRighteye_opendegree() {
                return righteye_opendegree;
            }

            public int getMouth_opendegree() {
                return mouth_opendegree;
            }
        }
    }
}