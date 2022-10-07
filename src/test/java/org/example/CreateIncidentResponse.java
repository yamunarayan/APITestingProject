package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateIncidentResponse {
    private Result result;
@Getter
    public static class Result {

        @JsonProperty("parent")
        private String parent;
        @JsonProperty("made_sla")
        private String madeSla;
        @JsonProperty("caused_by")
        private String causedBy;
        @JsonProperty("watch_list")
        private String watchList;
        @JsonProperty("upon_reject")
        private String uponReject;
        @JsonProperty("sys_updated_on")
        private String sysUpdatedOn;
        @JsonProperty("child_incidents")
        private String childIncidents;
        @JsonProperty("hold_reason")
        private String holdReason;
        @JsonProperty("origin_table")
        private String originTable;
        @JsonProperty("task_effective_number")
        private String taskEffectiveNumber;
        @JsonProperty("approval_history")
        private String approvalHistory;
        @JsonProperty("number")
        private String number;
        @JsonProperty("resolved_by")
        private String resolvedBy;
        @JsonProperty("sys_updated_by")
        private String sysUpdatedBy;
        @JsonProperty("opened_by")
        private OpenedBy openedBy;
        @JsonProperty("user_input")
        private String userInput;
        @JsonProperty("sys_created_on")
        private String sysCreatedOn;
        @JsonProperty("sys_domain")
        private SysDomain sysDomain;
        @JsonProperty("state")
        private String state;
        @JsonProperty("route_reason")
        private String routeReason;
        @JsonProperty("sys_created_by")
        private String sysCreatedBy;
        @JsonProperty("knowledge")
        private String knowledge;
        @JsonProperty("order")
        private String order;
        @JsonProperty("calendar_stc")
        private String calendarStc;
        @JsonProperty("closed_at")
        private String closedAt;
        @JsonProperty("cmdb_ci")
        private String cmdbCi;
        @JsonProperty("delivery_plan")
        private String deliveryPlan;
        @JsonProperty("contract")
        private String contract;
        @JsonProperty("impact")
        private String impact;
        @JsonProperty("active")
        private String active;
        @JsonProperty("work_notes_list")
        private String workNotesList;
        @JsonProperty("business_service")
        private String businessService;
        @JsonProperty("business_impact")
        private String businessImpact;
        @JsonProperty("priority")
        private String priority;
        @JsonProperty("sys_domain_path")
        private String sysDomainPath;
        @JsonProperty("rfc")
        private String rfc;
        @JsonProperty("time_worked")
        private String timeWorked;
        @JsonProperty("expected_start")
        private String expectedStart;
        @JsonProperty("opened_at")
        private String openedAt;
        @JsonProperty("business_duration")
        private String businessDuration;
        @JsonProperty("group_list")
        private String groupList;
        @JsonProperty("work_end")
        private String workEnd;
        @JsonProperty("caller_id")
        private String callerId;
        @JsonProperty("reopened_time")
        private String reopenedTime;
        @JsonProperty("resolved_at")
        private String resolvedAt;
        @JsonProperty("approval_set")
        private String approvalSet;
        @JsonProperty("subcategory")
        private String subcategory;
        @JsonProperty("work_notes")
        private String workNotes;
        @JsonProperty("universal_request")
        private String universalRequest;
        @JsonProperty("short_description")
        private String shortDescription;
        @JsonProperty("close_code")
        private String closeCode;
        @JsonProperty("correlation_display")
        private String correlationDisplay;
        @JsonProperty("delivery_task")
        private String deliveryTask;
        @JsonProperty("work_start")
        private String workStart;
        @JsonProperty("assignment_group")
        private String assignmentGroup;
        @JsonProperty("additional_assignee_list")
        private String additionalAssigneeList;
        @JsonProperty("business_stc")
        private String businessStc;
        @JsonProperty("cause")
        private String cause;
        @JsonProperty("description")
        private String description;
        @JsonProperty("origin_id")
        private String originId;
        @JsonProperty("calendar_duration")
        private String calendarDuration;
        @JsonProperty("close_notes")
        private String closeNotes;
        @JsonProperty("notify")
        private String notify;
        @JsonProperty("service_offering")
        private String serviceOffering;
        @JsonProperty("sys_class_name")
        private String sysClassName;
        @JsonProperty("closed_by")
        private String closedBy;
        @JsonProperty("follow_up")
        private String followUp;
        @JsonProperty("parent_incident")
        private String parentIncident;
        @JsonProperty("sys_id")
        private String sysId;
        @JsonProperty("contact_type")
        private String contactType;
        @JsonProperty("reopened_by")
        private String reopenedBy;
        @JsonProperty("incident_state")
        private String incidentState;
        @JsonProperty("urgency")
        private String urgency;
        @JsonProperty("problem_id")
        private String problemId;
        @JsonProperty("company")
        private String company;
        @JsonProperty("reassignment_count")
        private String reassignmentCount;
        @JsonProperty("activity_due")
        private String activityDue;
        @JsonProperty("assigned_to")
        private String assignedTo;
        @JsonProperty("severity")
        private String severity;
        @JsonProperty("comments")
        private String comments;
        @JsonProperty("approval")
        private String approval;
        @JsonProperty("sla_due")
        private String slaDue;
        @JsonProperty("comments_and_work_notes")
        private String commentsAndWorkNotes;
        @JsonProperty("due_date")
        private String dueDate;
        @JsonProperty("sys_mod_count")
        private String sysModCount;
        @JsonProperty("reopen_count")
        private String reopenCount;
        @JsonProperty("sys_tags")
        private String sysTags;
        @JsonProperty("escalation")
        private String escalation;
        @JsonProperty("upon_approval")
        private String uponApproval;
        @JsonProperty("correlation_id")
        private String correlationId;
        @JsonProperty("location")
        private String location;
        @JsonProperty("category")
        private String category;

    }
@Getter
    public static class SysDomain {

        @JsonProperty("link")
        private String link;
        @JsonProperty("value")
        private String value;

    }
@Getter
    public static class OpenedBy {

        @JsonProperty("link")
        private String link;
        @JsonProperty("value")
        private String value;

    }

}
