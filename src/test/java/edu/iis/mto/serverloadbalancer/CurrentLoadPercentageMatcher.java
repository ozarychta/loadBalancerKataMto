package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CurrentLoadPercentageMatcher extends TypeSafeMatcher<Server> {
    private double expectedLoadPercentage;

    public CurrentLoadPercentageMatcher(double expectedLoadPercentage) {
        this.expectedLoadPercentage = expectedLoadPercentage;
    }

    @Override
    protected boolean matchesSafely(Server server) {
        return expectedLoadPercentage == server.currentLoadPercentage
                || Math.abs(expectedLoadPercentage - server.currentLoadPercentage) < 0.01d;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a server with load percentage of ")
                .appendValue(expectedLoadPercentage);
    }
}
