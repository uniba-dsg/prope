package prope.metrics.installability;

import static org.junit.Assert.assertEquals;

import java.nio.file.Paths;

import org.junit.Test;

import prope.metrics.installability.util.GroupReader;

public class GroupReaderTests {
	
	@Test
	public void readeActiveBpel(){
		assertEquals("active",GroupReader.readGroupFromPath("abcactivebpelsdf"));
		assertEquals("active",GroupReader.readGroupFromPath("abcactive-bpelsdf"));
	}
	
	@Test
	public void readBpelG() {
		assertEquals("bpelg",GroupReader.readGroupFromPath("abcbpelgsdf"));
		assertEquals("bpelg",GroupReader.readGroupFromPath("abcac-bpel-gsdf"));
	}
	
	@Test
	public void readOde(){
		assertEquals("ode",GroupReader.readGroupFromPath("abcacodeelsdf"));
		assertEquals("ode",GroupReader.readGroupFromPath("abcacODElsdf"));
	}
	
	@Test
	public void readeOpenEsb(){
		assertEquals("openesb",GroupReader.readGroupFromPath("abcacopenesb23elsdf"));
		assertEquals("openesb",GroupReader.readGroupFromPath("abcaopenesbElsdf"));
	}
	
	@Test
	public void readOrchestra(){
		assertEquals("orchestra",GroupReader.readGroupFromPath("abcaorchestraeelsdf"));
	}
	
	@Test
	public void readPetals(){
		assertEquals("petals",GroupReader.readGroupFromPath("abcapetalseelsdf"));
	}
	
	@Test
	public void readFromPath(){
		assertEquals("petals",GroupReader.readGroupFromPath(Paths.get("abcapetalseelsdf")));
	}

}
