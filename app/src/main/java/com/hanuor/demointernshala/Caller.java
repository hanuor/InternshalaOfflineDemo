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
    public Caller(String url, String req_method, String answer,String stat, String requestType, String extras, String id,String response){
        this.answer = answer;
        this.reg_url = url;
        this.req_method = req_method;
        this.stat =stat;
        this.extras = extras;
        this.response = response;
        this.id = id;
        this.requestType = requestType;
    }
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    private String id;
    private String requestType;
    private String extras;
    private String response;
    private String stat;



}
