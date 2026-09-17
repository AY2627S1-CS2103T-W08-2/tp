package seedu.address.logic.commands;

import static seedu.address.logic.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;
import seedu.address.testutil.PersonBuilder;

public class RemarkCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addRemark_success() {
        Remark remark = new Remark("Likes baseball");
        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personToEdit).withRemark(remark.value).build();
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToEdit, editedPerson);

        RemarkCommand command = new RemarkCommand(INDEX_FIRST_PERSON, remark);
        String expectedMessage = String.format(RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS,
                seedu.address.logic.Messages.format(editedPerson));

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_removeRemark_success() {
        Model modelWithRemark = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Person personToEdit = modelWithRemark.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person personWithRemark = new PersonBuilder(personToEdit).withRemark("Existing remark").build();
        modelWithRemark.setPerson(personToEdit, personWithRemark);

        Person editedPerson = new PersonBuilder(personWithRemark).withRemark("").build();
        Model expectedModel = new ModelManager(modelWithRemark.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(personWithRemark, editedPerson);

        RemarkCommand command = new RemarkCommand(INDEX_FIRST_PERSON, new Remark(""));
        String expectedMessage = String.format(RemarkCommand.MESSAGE_DELETE_REMARK_SUCCESS,
                seedu.address.logic.Messages.format(editedPerson));

        assertCommandSuccess(command, modelWithRemark, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        RemarkCommand command = new RemarkCommand(INDEX_SECOND_PERSON, new Remark("Likes baseball"));
        Model modelWithOnePerson = new ModelManager();
        modelWithOnePerson.addPerson(model.getFilteredPersonList().get(0));

        assertCommandFailure(command, modelWithOnePerson, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

}
