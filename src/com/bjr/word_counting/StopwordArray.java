package com.bjr.word_counting;

import java.util.ArrayList;


public class StopwordArray {
	
	private ArrayList<String> array;

	public StopwordArray() {
		
		array = new ArrayList<String>();
		array.add("aber");
		array.add("alle");
		array.add("alles");
		array.add("als");
		array.add("also");
		array.add("andere");
		array.add("anderem");
		array.add("anderer");
		array.add("anderes");
		array.add("anders");
		array.add("auf");
		array.add("aus");
		array.add("ausser");
		array.add("ausserdem");
		array.add("bei");
		array.add("beide");
		array.add("beiden");
		array.add("beides");
		array.add("beim");
		array.add("bereits");
		array.add("bestehen");
		array.add("bevor");
		array.add("bin");
		array.add("bis");
		array.add("bloss");
		array.add("braucht");
		array.add("dabei");
		array.add("dadurch");
		array.add("dagegen");
		array.add("daher");
		array.add("damit");
		array.add("danach");
		array.add("dann");
		array.add("darf");
		array.add("dar�ber");
		array.add("darum");
		array.add("darunter");
		array.add("das");
		array.add("dass");
		array.add("davon");
		array.add("dazu");
		array.add("dem");
		array.add("den");
		array.add("denn");
		array.add("der");
		array.add("des");
		array.add("deshalb");
		array.add("dessen");
		array.add("die");
		array.add("dies");
		array.add("diese");
		array.add("diesem");
		array.add("diesen");
		array.add("dieser");
		array.add("dieses");
		array.add("doch");
		array.add("dort");
		array.add("d�rfen");
		array.add("durch");
		array.add("durfte");
		array.add("durften");
		array.add("ebenfalls");
		array.add("ebenso");
		array.add("ein");
		array.add("eine");
		array.add("einem");
		array.add("einen");
		array.add("einer");
		array.add("eines");
		array.add("einige");
		array.add("einiges");
		array.add("einzig");
		array.add("entweder");
		array.add("erst");
		array.add("erste");
		array.add("ersten");
		array.add("etwa");
		array.add("etwas");
		array.add("falls");
		array.add("fast");
		array.add("ferner");
		array.add("folgender");
		array.add("folglich");
		array.add("f�r");
		array.add("ganz");
		array.add("geben");
		array.add("gegen");
		array.add("gehabt");
		array.add("gekonnt");
		array.add("gem��");
		array.add("getan");
		array.add("gewesen");
		array.add("gewollt");
		array.add("geworden");
		array.add("gibt");
		array.add("habe");
		array.add("haben");
		array.add("h�tte");
		array.add("h�tten");
		array.add("hallo");
		array.add("hat");
		array.add("hatte");
		array.add("hatten");
		array.add("heraus");
		array.add("herein");
		array.add("hier");
		array.add("hin");
		array.add("hinein");
		array.add("hinter");
		array.add("ich");
		array.add("ihm");
		array.add("ihn");
		array.add("ihnen");
		array.add("ihr");
		array.add("ihre");
		array.add("ihrem");
		array.add("ihren");
		array.add("ihres");
		array.add("im");
		array.add("immer");
		array.add("in");
		array.add("indem");
		array.add("infolge");
		array.add("innen");
		array.add("innerhalb");
		array.add("ins");
		array.add("inzwischen");
		array.add("irgend");
		array.add("irgendwas");
		array.add("irgendwen");
		array.add("irgendwer");
		array.add("irgendwie");
		array.add("irgendwo");
		array.add("ist");
		array.add("jede");
		array.add("jedem");
		array.add("jeden");
		array.add("jeder");
		array.add("jedes");
		array.add("jedoch");
		array.add("jene");
		array.add("jenem");
		array.add("jenen");
		array.add("jener");
		array.add("jenes");
		array.add("kann");
		array.add("kein");
		array.add("keine");
		array.add("keinem");
		array.add("keinen");
		array.add("keiner");
		array.add("keines");
		array.add("k�nnen");
		array.add("k�nnte");
		array.add("k�nnten");
		array.add("konnte");
		array.add("konnten");
		array.add("k�nftig");
		array.add("leer");
		array.add("machen");
		array.add("macht");
		array.add("machte");
		array.add("machten");
		array.add("man");
		array.add("mehr");
		array.add("mein");
		array.add("meine");
		array.add("meinen");
		array.add("meinem");
		array.add("meiner");
		array.add("meist");
		array.add("meiste");
		array.add("meisten");
		array.add("mich");
		array.add("mit");
		array.add("m�chte");
		array.add("m�chten");
		array.add("m�ssen");
		array.add("m�ssten");
		array.add("muss");
		array.add("musste");
		array.add("mussten");
		array.add("nach");
		array.add("nachdem");
		array.add("nacher");
		array.add("n�mlich");
		array.add("neben");
		array.add("nein");
		array.add("nicht");
		array.add("nichts");
		array.add("noch");
		array.add("n�tzt");
		array.add("nur");
		array.add("nutzt");
		array.add("obgleich");
		array.add("obwohl");
		array.add("oder");
		array.add("ohne");
		array.add("per");
		array.add("pro");
		array.add("rund");
		array.add("schon");
		array.add("sehr");
		array.add("seid");
		array.add("sein");
		array.add("seine");
		array.add("seinem");
		array.add("seinen");
		array.add("seiner");
		array.add("seit");
		array.add("seitdem");
		array.add("seither");
		array.add("selber");
		array.add("sich");
		array.add("sie");
		array.add("siehe");
		array.add("sind");
		array.add("sobald");
		array.add("solange");
		array.add("solch");
		array.add("solche");
		array.add("solchem");
		array.add("solchen");
		array.add("solcher");
		array.add("solches");
		array.add("soll");
		array.add("sollen");
		array.add("sollte");
		array.add("sollten");
		array.add("somit");
		array.add("sondern");
		array.add("soweit");
		array.add("sowie");
		array.add("sp�ter");
		array.add("stets");
		array.add("such");
		array.add("�ber");
		array.add("ums");
		array.add("und");
		array.add("uns");
		array.add("unser");
		array.add("unsere");
		array.add("unserem");
		array.add("unseren");
		array.add("viel");
		array.add("viele");
		array.add("vollst�ndig");
		array.add("vom");
		array.add("von");
		array.add("vor");
		array.add("vorbei");
		array.add("vorher");
		array.add("vor�ber");
		array.add("w�hrend");
		array.add("w�re");
		array.add("w�ren");
		array.add("wann");
		array.add("war");
		array.add("waren");
		array.add("warum");
		array.add("was");
		array.add("wegen");
		array.add("weil");
		array.add("weiter");
		array.add("weitere");
		array.add("weiterem");
		array.add("weiteren");
		array.add("weiterer");
		array.add("weiteres");
		array.add("weiterhin");
		array.add("welche");
		array.add("welchem");
		array.add("welchen");
		array.add("welcher");
		array.add("welches");
		array.add("wem");
		array.add("wen");
		array.add("wenigstens");
		array.add("wenn");
		array.add("wenngleich");
		array.add("wer");
		array.add("werde");
		array.add("werden");
		array.add("weshalb");
		array.add("wessen");
		array.add("wie");
		array.add("wieder");
		array.add("will");
		array.add("wir");
		array.add("wird");
		array.add("wodurch");
		array.add("wohin");
		array.add("wollen");
		array.add("wollte");
		array.add("wollten");
		array.add("worin");
		array.add("w�rde");
		array.add("w�rden");
		array.add("wurde");
		array.add("wurden");
		array.add("zufolge");
		array.add("zum");
		array.add("zusammen");
		array.add("zur");
		array.add("zwar");
		array.add("zwischen");
		
		
		array.add("0");
		array.add("1");
		array.add("2");
		array.add("3");
		array.add("4");
		array.add("5");
		array.add("6");
		array.add("7");
		array.add("8");
		array.add("9");

	}

	public ArrayList<String> getArray() {
		return array;
	}

}