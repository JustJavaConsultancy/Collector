package ng.com.sokoto.dto;

import io.swagger.annotations.ApiModel;
import java.time.LocalDate;
import java.util.Collection;
import ng.com.sokoto.annotation.CheckDate;
import ng.com.sokoto.web.domain.JournalLine;

@ApiModel
public class JournalDto extends AbstractDto<String> {

    private String id;
    private String refNumber;

    @CheckDate
    private LocalDate entryDate;

    private Collection<JournalLine> journalLines;
    private String description;

    public JournalDto() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRefNumber(String refNumber) {
        this.refNumber = refNumber;
    }

    public String getRefNumber() {
        return this.refNumber;
    }

    public void setEntryDate(java.time.LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public java.time.LocalDate getEntryDate() {
        return this.entryDate;
    }

    public void setJournalLines(java.util.Collection<JournalLine> journalLines) {
        this.journalLines = journalLines;
    }

    public java.util.Collection<JournalLine> getJournalLines() {
        return this.journalLines;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
