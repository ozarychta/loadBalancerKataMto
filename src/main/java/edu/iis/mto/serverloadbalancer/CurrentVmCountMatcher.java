package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CurrentVmCountMatcher extends TypeSafeMatcher<Server> {
    private int expectedVmCount;

    public CurrentVmCountMatcher(int expectedVmCount) {
        this.expectedVmCount = expectedVmCount;
    }

    @Override
    protected boolean matchesSafely(Server server) {
        return expectedVmCount == server.countVms();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a server with vmos count of ").appendValue(expectedVmCount);
    }

    @Override
    protected void describeMismatchSafely(Server item, Description mismatchDescription) {
        mismatchDescription.appendText("a server with vmos count of ").appendValue(item.countVms());
    }

    public static Matcher<Server> hasVmCountOf(int expectedVmCount) {
        return new CurrentVmCountMatcher(expectedVmCount);
    }

}
