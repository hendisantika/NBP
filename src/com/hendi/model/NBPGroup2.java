package com.hendi.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class NBPGroup2 {

    @XmlElement(name = "NBP")
    private List<NBP> bps = new ArrayList<>();

	public List<NBP> getBps() {
		return bps;
	}

	public void setBps(List<NBP> bps) {
		this.bps = bps;
	}

}
