


//

//




//




//



package com.openbravo.pos.reports;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.BaseSentence;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.QBFBuilder;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.user.EditorCreator;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 
 */
public class PanelReportBean extends JPanelReport {
    
    private String title;
    private String report;
    
    private String resourcebundle = null;
    
    private String sentence;
    
    private List<Datas> fielddatas = new ArrayList<Datas>();
    private List<String> fieldnames = new ArrayList<String>();
    
    private List<String> paramnames = new ArrayList<String>();
    
    private JParamsComposed qbffilter = new JParamsComposed();
    
    @Override
    public void init(AppView app) throws BeanFactoryException {   
        
        qbffilter.init(app);       
        super.init(app);
    }
    
    @Override
    public void activate() throws BasicException {
        
        qbffilter.activate();
        super.activate();
        
        if (qbffilter.isEmpty()) {
            setVisibleFilter(false);
            setVisibleButtonFilter(false);
        }
    }
    
    @Override
    protected EditorCreator getEditorCreator() {
        
        return qbffilter;
    }    
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setTitleKey(String titlekey) {
        title = AppLocal.getIntString(titlekey);
    }
    
    public String getTitle() {
        return title;
    } 
    
    public void setReport(String report) {
        this.report = report;
    }
    
    protected String getReport() {
        return report;
    }  
    
    public void setResourceBundle(String resourcebundle) {
        this.resourcebundle = resourcebundle;
    }
    
    protected String getResourceBundle() {
        return resourcebundle == null 
                ? report 
                : resourcebundle;
    }    
    
    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
    
    public void addField(String name, Datas data) {
        fieldnames.add(name);
        fielddatas.add(data);
    }
    
    public void addParameter(String name) {
        paramnames.add(name);        
    }
    
    protected BaseSentence getSentence() {
        return new StaticSentence(m_App.getSession()
            , new QBFBuilder(sentence, paramnames.toArray(new String[paramnames.size()]))
            , qbffilter.getSerializerWrite()
            , new SerializerReadBasic(fielddatas.toArray(new Datas[fielddatas.size()])));
    }
    
    protected ReportFields getReportFields() {
        return new ReportFieldsArray(fieldnames.toArray(new String[fieldnames.size()]));
    }       
    
    public void addQBFFilter(ReportEditorCreator qbff) {
        qbffilter.addEditor(qbff);
    }    
}
