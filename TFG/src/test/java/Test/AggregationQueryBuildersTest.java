package Test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import Mongo.AggregationQueryBuilders;

public class AggregationQueryBuildersTest {

	@Test
	public void test() {

		AggregationQueryBuilders Test = new AggregationQueryBuilders();
		ProjectionStage(Test);
		UnwindStage(Test);
		GroupStage(Test);

	}

	public void ProjectionStage(AggregationQueryBuilders Test) {
		Test.clearStageBooleans();
		boolean excludeId = true;

		ArrayList<String> fields = new ArrayList<String>();
		fields.add("pepe");
		fields.add("pepe");
		Test.makeProjectStage(fields, excludeId);

		Assert.assertTrue(Test.isProjectStaga());

	}

	public void UnwindStage(AggregationQueryBuilders Test) {

		Test.clearStageBooleans();
		Test.makeUnwindStage("treeeee");
		Assert.assertTrue(Test.isUndwinStage());
	}

	public void GroupStage(AggregationQueryBuilders Test) {
		Test.clearStageBooleans();
		Test.makeGroupStage("jajaja", "jejeje", 1323);
		Assert.assertTrue(Test.isGroupStage());
	}

}
