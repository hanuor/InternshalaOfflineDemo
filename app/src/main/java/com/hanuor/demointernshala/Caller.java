package com.hanuor.demointernshala;
/*
 * Copyright (C) 2016 Hanuor Inc. by Shantanu Johri(https://hanuor.github.io/shanjohri/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class Caller {
    public String getReg_url() {
        return reg_url;
    }

    public void setReg_url(String reg_url) {
        this.reg_url = reg_url;
    }

    public String getReq_method() {
        return req_method;
    }

    public void setReq_method(String req_method) {
        this.req_method = req_method;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    private String reg_url;
    private String req_method;
    private String answer;


    private String stat;

    public Caller(String url, String req_method, String answer,String stat){
        this.answer = answer;
        this.reg_url = url;
        this.req_method = req_method;
        this.stat =stat;

    }

}
