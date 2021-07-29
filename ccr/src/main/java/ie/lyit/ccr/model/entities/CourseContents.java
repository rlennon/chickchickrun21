package ie.lyit.ccr.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "coursecontents")
public class CourseContents {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "course_id")
    private Integer courseId;

    @Column(name = "page_no")
    private Integer pageNo;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return this.courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getPageNo() {
        return this.pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
