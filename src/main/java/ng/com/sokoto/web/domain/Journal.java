package ng.com.sokoto.web.domain;

import java.time.LocalDate;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "journal")
public class Journal {

    public static final String SEQUENCE_NAME = "journal_sequence";

    @Id
    private String refNumber;

    private LocalDate entryDate;
    private Collection<JournalLine> journalLines;
    private String description;

    public String getRefNumber() {
        return refNumber;
    }

    public void setRefNumber(String refNumber) {
        this.refNumber = refNumber;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public Collection<JournalLine> getJournalLines() {
        return journalLines;
    }

    public void setJournalLines(Collection<JournalLine> journalLines) {
        this.journalLines = journalLines;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return (
            "Journal{" +
            "refNumber='" +
            refNumber +
            '\'' +
            ", entryDate=" +
            entryDate +
            ", journalLines=" +
            journalLines +
            ", description='" +
            description +
            '\'' +
            '}'
        );
    }
}
