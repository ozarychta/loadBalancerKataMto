package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ServerVmsCountMatcher extends TypeSafeMatcher<Server> {
    private int expectedVmsCount;

    public ServerVmsCountMatcher(int expectedVmsCount) {
        this.expectedVmsCount = expectedVmsCount;
    }

    @Override
    protected boolean matchesSafely(Server server) {
        return expectedVmsCount == server.countVms();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("server with vms count of").appendValue(expectedVmsCount);
    }

    @Override
    protected void describeMismatchSafely(Server item, Description mismatchDescription) {
        mismatchDescription.appendText("server with vms count of").appendValue(item.countVms());
    }


    public static ServerVmsCountMatcher hasAVmsCountOf(int expectedVmsCount) {
        return new ServerVmsCountMatcher(expectedVmsCount);
    }
}
