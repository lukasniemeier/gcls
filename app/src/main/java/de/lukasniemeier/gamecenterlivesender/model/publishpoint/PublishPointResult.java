package de.lukasniemeier.gamecenterlivesender.model.publishpoint;

import android.content.Context;

import org.droidparts.annotation.serialize.XML;
import org.droidparts.model.Entity;
import org.droidparts.persist.serializer.XMLSerializer;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class PublishPointResult extends Entity {

    @XML(tag = "result" + XML.SUB + "path")
    public String path;

    public static PublishPointResult parse(String body, Context ctx) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document xml = builder.parse(new InputSource(new StringReader(body)));

        XMLSerializer<PublishPointResult> serializer =
                new XMLSerializer<PublishPointResult>(PublishPointResult.class, ctx);

        return serializer.deserialize(xml);
    }
}
