package com.github.sd4324530.fastweixin.message;

import com.github.sd4324530.fastweixin.message.util.MessageBuilder;

public class CustomMsg extends BaseMsg {

    private String kfAccount;

    public CustomMsg(String kfAccount) {
        this.kfAccount = kfAccount;
    }

    public String getKfAccount() {
        return kfAccount;
    }

    public void setKfAccount(String kfAccount) {
        this.kfAccount = kfAccount;
    }

    @Override
    public String toXml() {
        MessageBuilder mb = new MessageBuilder(super.toXml());
        mb.addData("MsgType", RespType.KF);
        mb.append("<TransInfo>\n");
        mb.addData("KfAccount", kfAccount);
        mb.append("</TransInfo>\n");
        mb.surroundWith("xml");
        return mb.toString();
    }

}
