package com.scottehboeh.glc.objects;

import com.scottehboeh.glc.enums.EnumMediaType;

import java.util.Date;

/**
 * Created by 1503257 on 13/12/2017.
 */
public class ObjectStockJournal extends ObjectStock {

    private int ISSN;
    private int journalIssueNumber;
    private Date journalDate;
    private String journalTitle;
    private String journalSubjectArea;
    private String journalPublisher;
    private int journalNumberOfPages;

    public ObjectStockJournal() {
        setMediaType(EnumMediaType.JOURNAL);
    }

    public int getISSN() {
        return ISSN;
    }

    public void setISSN(int ISSN) {
        this.ISSN = ISSN;
    }

    public int getJournalIssueNumber() {
        return journalIssueNumber;
    }

    public void setJournalIssueNumber(int journalIssueNumber) {
        this.journalIssueNumber = journalIssueNumber;
    }

    public Date getJournalDate() {
        return journalDate;
    }

    public void setJournalDate(Date journalDate) {
        this.journalDate = journalDate;
    }

    public String getJournalTitle() {
        return journalTitle;
    }

    public void setJournalTitle(String journalTitle) {
        this.journalTitle = journalTitle;
    }

    public String getJournalSubjectArea() {
        return journalSubjectArea;
    }

    public void setJournalSubjectArea(String journalSubjectArea) {
        this.journalSubjectArea = journalSubjectArea;
    }

    public String getJournalPublisher() {
        return journalPublisher;
    }

    public void setJournalPublisher(String journalPublisher) {
        this.journalPublisher = journalPublisher;
    }

    public int getJournalNumberOfPages() {
        return journalNumberOfPages;
    }

    public void setJournalNumberOfPages(int journalNumberOfPages) {
        this.journalNumberOfPages = journalNumberOfPages;
    }

    @Override
    public String getUnderstandableName() {
        return this.journalTitle;
    }
}
