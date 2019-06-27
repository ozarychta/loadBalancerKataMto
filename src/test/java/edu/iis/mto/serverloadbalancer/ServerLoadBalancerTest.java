package edu.iis.mto.serverloadbalancer;

import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.hasLoadPercentageOf;
import static edu.iis.mto.serverloadbalancer.CurrentVmCountMatcher.hasVmCountOf;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.VmBuilder.vm;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matcher;
import org.junit.Test;

public class ServerLoadBalancerTest {
	@Test
	public void itCompiles() {
		assertThat(true, equalTo(true));
	}

	@Test
	public void balancingAServer_noVms_serverStaysEmpty() {
		Server theServer = a(server().withCapacity(1));

		balance(aListOfServersWith(theServer), anEmptyListOfVms());

		assertThat(theServer, hasLoadPercentageOf(0.0d));
	}

	@Test
	public void balancingOneServerServerWithOneSlotCapacity_andOneVm_fillsTheServerWithTheVm() {
		Server theServer = a(server().withCapacity(1));
		Vm theVm = a(vm().ofSize(1));

		balance(aListOfServersWith(theServer), aListOfVms(theVm));

		assertThat(theServer, hasLoadPercentageOf(100.0d));
	}

	@Test
	public void balancingOneServerWithTenSlots_andOneSlotVm_fillsTheServerWithTenPercent() {
		Server theServer = a(server().withCapacity(10));
		Vm theVm = a(vm().ofSize(1));

		balance(aListOfServersWith(theServer), aListOfVms(theVm));

		assertThat(theServer, hasLoadPercentageOf(10.0d));
	}

	@Test
	public void balancingAServerWithEnoughRoom_getsFilledWithAllVms() {
		Server theServer = a(server().withCapacity(10));
		Vm theVm = a(vm().ofSize(1));
		Vm theVm2 = a(vm().ofSize(2));

		balance(aListOfServersWith(theServer), aListOfVms(theVm, theVm2));

		assertThat(theServer, hasVmCountOf(2));

		assertThat("server contains vm1 ", theServer.contains(theVm));
		assertThat("server contains vm2 ", theServer.contains(theVm2));
	}

	@Test
	public void aVm_shouldBeBalancedOnLessLoadedServerFirst() {
		Server moreLoadedServer = a(server().withCapacity(10).withCurrentLoad(50.0d));
		Server lessLoadedServer = a(server().withCapacity(10).withCurrentLoad(40.0d));
		Vm theVm = a(vm().ofSize(1));

		balance(aListOfServersWith(moreLoadedServer, lessLoadedServer), aListOfVms(theVm));

		assertThat("less loaded server should contain vm ", lessLoadedServer.contains(theVm));
		assertThat("more loaded server should not contain vm ", !moreLoadedServer.contains(theVm));
	}

	@Test
	public void balanceAServerWithNoEnoughRoom_shouldNotBeFilledWithAVm() {
		Server theServer = a(server().withCapacity(10).withCurrentLoad(95.0d));
		Vm theVm = a(vm().ofSize(2));

		balance(aListOfServersWith(theServer), aListOfVms(theVm));

		assertThat(" server should not contain vm ", !theServer.contains(theVm));
	}


	private Server[] aListOfServersWith(Server... servers) {
		return servers;
	}


	private Vm[] aListOfVms(Vm... vms) {
		return vms;
	}



	private void balance(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers, vms);
	}

	private Vm[] anEmptyListOfVms() {
		return new Vm[0];
	}

	private <T> T a(Builder<T> builder) {
		return builder.build();
	}


}
