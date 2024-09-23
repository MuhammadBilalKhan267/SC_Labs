/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package rules;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * JUnit tests for RulesOf6005.
 */
public class RulesOf6005Test {

    /**
     * Tests the mayUseCodeInAssignment method.
     */
    @Test
    public void testMayUseCodeInAssignment() {
        // Existing test cases
        assertFalse("Expected false: un-cited publicly-available code",
                RulesOf6005.mayUseCodeInAssignment(false, true, false, false, false));
        assertTrue("Expected true: self-written required code",
                RulesOf6005.mayUseCodeInAssignment(true, false, true, true, true));
    }

    // New test cases

    @Test
    public void testWrittenByYourself() {
        // If written by yourself, you can use it
        assertTrue("Expected true: self-written code",
                RulesOf6005.mayUseCodeInAssignment(true, false, false, false, false));
    }

    @Test
    public void testCourseWorkWithoutCitation() {
        // If it's course work, even without citation, it should be allowed
        assertTrue("Expected true: course work without citation",
                RulesOf6005.mayUseCodeInAssignment(false, false, true, false, false));
    }

    @Test
    public void testPublicCodeWithoutCitation() {
        // Publicly available code without citing is not allowed
        assertFalse("Expected false: public code without citation",
                RulesOf6005.mayUseCodeInAssignment(false, true, false, false, false));
    }

    @Test
    public void testPublicCodeWithCitation() {
        // Publicly available code with citation is allowed
        assertTrue("Expected true: public code with citation",
                RulesOf6005.mayUseCodeInAssignment(false, true, false, true, false));
    }

    @Test
    public void testImplementationRequiredButNotSelfWritten() {
        // Implementation is required, but it's not self-written, so not allowed
        assertFalse("Expected false: implementation required but not self-written",
                RulesOf6005.mayUseCodeInAssignment(false, false, false, true, true));
    }

    @Test
    public void testImplementationRequiredAndSelfWritten() {
        // Implementation is required, and it's self-written, so it's allowed
        assertTrue("Expected true: implementation required and self-written",
                RulesOf6005.mayUseCodeInAssignment(true, false, false, true, true));
    }

    @Test
    public void testCitingSourceButNoImplementationRequired() {
        // Citation is provided, and implementation is not required, so it's allowed
        assertTrue("Expected true: citing source and no implementation required",
                RulesOf6005.mayUseCodeInAssignment(false, true, false, true, false));
    }

    @Test
    public void testNotCitingSourceWhenRequired() {
        // Citation required but not provided, so not allowed
        assertFalse("Expected false: citation required but not provided",
                RulesOf6005.mayUseCodeInAssignment(false, true, false, false, false));
    }
}
