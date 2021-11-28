/**
 * @author kemal
 * @since 28.11.2021
 */
public class Consumer implements Runnable {

	private static BoundedBlockingQueue boundedBlockingQueue;

	public Consumer(BoundedBlockingQueue boundedBlockingQueue) {
		this.boundedBlockingQueue = boundedBlockingQueue;
	}

	@Override
	public void run() {
		try {
			while (true) {
				boundedBlockingQueue.remove();
				Thread.sleep(5000);
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
