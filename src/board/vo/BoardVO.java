package board.vo;

import java.sql.Date;

public class BoardVO {
    private int boardId;
    private String title;
    private String content;
    private String writer;
    private Date writeDate;

    public int getBoardId() { return boardId; }
    public void setBoardId(int boardId) { this.boardId = boardId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getWriter() { return writer; }
    public void setWriter(String writer) { this.writer = writer; }

    public Date getWriteDate() { return writeDate; }
    public void setWriteDate(Date writeDate) { this.writeDate = writeDate; }

    @Override
    public String toString() {
        return boardId + " | " + title + " | " + writer + " | " + writeDate;
    }
}
