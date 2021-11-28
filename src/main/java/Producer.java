/**
 * @author kemal
 * @since 28.11.2021
 */
public class Producer implements Runnable {
	private static BoundedBlockingQueue boundedBlockingQueue;
	private final int element;

	public Producer(BoundedBlockingQueue boundedBlockingQueue, int element) {
		this.boundedBlockingQueue = boundedBlockingQueue;
		this.element = element;
	}

	@Override
	public void run() {
		try {
			boundedBlockingQueue.add(element);
			Thread.sleep(100);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
