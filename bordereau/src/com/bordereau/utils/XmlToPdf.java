package com.bordereau.utils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.xmlgraphics.util.MimeConstants;

public class XmlToPdf {
    public static void print() throws IOException {
        File xsltFile = new File("C:/Temp/AMVtemplate.xsl");
        StreamSource xmlSource = new StreamSource(new File("C:/Temp/AMVdocuments.xml"));
        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        OutputStream out = null;
        
        try {
            out = new java.io.FileOutputStream("C:/Temp/AMVout.pdf");
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
            
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));

            Result res = new SAXResult(fop.getDefaultHandler());

            transformer.transform(xmlSource, res);

        } catch (Exception ex) {
            Log.msg(1, ex.getMessage());
        }
        finally {
            out.close();
        }           
    }        
}
