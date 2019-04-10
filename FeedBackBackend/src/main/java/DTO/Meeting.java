package DTO;

public class Meeting {

    private String name;
    private String topic;
    private String place;
    private int state; // 0 ikke starte, 1 igang, 2 slut.
    private int day;
    private int month;
    private int year;
    private String realStartTime;
    private String realendTime;
    private String meetingID;
    private String userMeetingID;
    private String startTime;
    private String endTime;
    private String expectedDuration;
    private String CreatedById;

    public Meeting() {
    }

    public Meeting(String name, String topic, String place, int state, int day, int month, int year, String realStartTime, String realendTime, String meetingID, String userMeetingID, String startTime, String endTime) {
        this.name = name;
        this.topic = topic;
        this.place = place;
        this.state = state;
        this.day = day;
        this.month = month;
        this.year = year;
        this.realStartTime = realStartTime;
        this.realendTime = realendTime;
        this.meetingID = meetingID;
        this.userMeetingID = userMeetingID;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Meeting(String name, String topic, String place, int day, int month, int year, String startTime, String endTime, String createdById) {
        this.name = name;
        this.topic = topic;
        this.place = place;
        this.day = day;
        this.month = month;
        this.year = year;
        this.startTime = startTime;
        this.endTime = endTime;
        this.CreatedById = createdById;
    }

    public String getExpectedDuration() {
        return expectedDuration;
    }

    public String getCreatedById() {
        return CreatedById;
    }

    public void setCreatedById(String createdById) {
        CreatedById = createdById;
    }

    public void setExpectedDuration(String expectedDuration) {
        this.expectedDuration = expectedDuration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getRealStartTime() {
        return realStartTime;
    }

    public void setRealStartTime(String realStartTime) {
        this.realStartTime = realStartTime;
    }

    public String getRealendTime() {
        return realendTime;
    }

    public void setRealendTime(String realendTime) {
        this.realendTime = realendTime;
    }

    public String getMeetingID() {
        return meetingID;
    }

    public void setMeetingID(String meetingID) {
        this.meetingID = meetingID;
    }

    public String getUserMeetingID() {
        return userMeetingID;
    }

    public void setUserMeetingID(String userMeetingID) {
        this.userMeetingID = userMeetingID;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
