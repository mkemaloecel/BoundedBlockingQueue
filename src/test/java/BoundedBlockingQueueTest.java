import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
/**
 * @author kemal
 * @since 28.11.2021
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BoundedBlockingQueueTest {

	@Test
	public void addingElementTest() {

		ExecutorService executor = Executors.newFixedThreadPool(10);
		BoundedBlockingQueue boundedBlockingQueue = new BoundedBlockingQueueImpl(2);

		for (int i = 1; i < 101; i++) {
			executor.execute(new Producer(boundedBlockingQueue, i));
			executor.execute(new Consumer(boundedBlockingQueue));
			int x = i + 1;
			executor.execute(new Producer(boundedBlockingQueue, x));
		}

		executor.shutdown();

		System.out.println("Finished all threads");
		Assert.assertFalse(executor.isTerminated());
	}
}
