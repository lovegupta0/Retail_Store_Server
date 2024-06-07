package com.server.uid.ServerUID.Service;

import com.server.uid.ServerUID.Model.UID;
import com.server.uid.ServerUID.Repo.UIDRepo;
import com.server.uid.ServerUID.UID.GenerateAlpha;
import com.server.uid.ServerUID.UID.GenerateNum;
import com.server.uid.ServerUID.UID.GenerateSymbol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenerateUID {
    @Autowired
    private UIDRepo repo;
    @Autowired
    private GenerateNum generateNum;
    @Autowired
    private GenerateAlpha generateAlpha;
    @Autowired
    private GenerateSymbol generateSymbol;

    public String getUID(){
        UID id=repo.findById(1).orElse(null);

        if(id==null){
            id=new UID(generateSymbol.getSymbol(""),generateAlpha.getAlpha(""),"0000",false,false,false);
            repo.save(id);

        }
        else{
            if(id.isAlphFull()){
                id.setApha(generateAlpha.getAlpha(id.getApha()));
                id.setAlphFull(false);
            }
            else if(id.isSymbolFull()){
                id.setSymbol(generateSymbol.getSymbol(id.getSymbol()));
                id.setSymbolFull(false);
            }
            else {
                id.setNum(generateNum.getNum(Integer.parseInt(id.getNum())));
            }
            if(id.getNum().equals("9999")){
                id.setSymbolFull(true);
            }
            else if(id.getSymbol().equals("++++")){
                id.setAlphFull(true);
            }
            repo.save(id);
        }
        StringBuilder sb=new StringBuilder(id.getApha());
        sb.append('$');
        sb.append(id.getSymbol());
        sb.append('$');
        sb.append(id.getNum());
        return sb.toString();
    }
}
