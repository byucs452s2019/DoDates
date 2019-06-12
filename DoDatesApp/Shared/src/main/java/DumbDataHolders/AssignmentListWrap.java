package DumbDataHolders;

import java.util.List;

import model.Assignment;

public class AssignmentListWrap {

    public AssignmentListWrap(List<Assignment> assignmentList) {
        this.assignmentList = assignmentList;
    }

    public List<Assignment> assignmentList;
}
