package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import seedu.address.model.meeting.exceptions.DuplicateMeetingException;
import seedu.address.model.meeting.exceptions.MeetingNotFoundException;

//@@author AyushChatto
/**
 * A list of meetings that enforces uniqueness between its elements and does not allow nulls.
 */
public class UniqueMeetingList implements Iterable<Meeting> {
    private final ArrayList<Meeting> allMeetings = new ArrayList<>();

    /**
     * Returns true if the list contains an equivalent value as the given argument.
     */
    public boolean contains(Meeting toCheck) {
        requireNonNull(toCheck);
        return allMeetings.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a value to the list.
     * No value at the same time can exist in the list.
     */
    public void add(Meeting toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateMeetingException();
        }
        allMeetings.add(toAdd);
    }

    /**
     * Replaces the value {@code target} in the list with {@code editedMeeting}.
     * {@code target} must exist in the list.
     */
    public void setMeeting(Meeting target, Meeting editedMeeting) {
        requireAllNonNull(target, editedMeeting);

        int index = allMeetings.indexOf(target);
        if (index == -1) {
            throw new MeetingNotFoundException();
        }

        allMeetings.set(index, editedMeeting);
    }

    /**
     * Removes the equivalent value from the list.
     * The value must exist in the list.
     */
    public void remove(Meeting toRemove) {
        requireNonNull(toRemove);
        if (!allMeetings.remove(toRemove)) {
            throw new MeetingNotFoundException();
        }
    }

    @Override
    public Iterator<Meeting> iterator() {
        return allMeetings.iterator();
    }

    /**
     * Returns true if {@code meetings} contains only unique meetings.
     */
    private boolean meetingsAreUnique(List<Meeting> meetings) {
        for (int i = 0; i < meetings.size() - 1; i++) {
            for (int j = i + 1; j < meetings.size(); j++) {
                if (meetings.get(i).equals(meetings.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
