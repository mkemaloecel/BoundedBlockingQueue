import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * @author kemal
 * @since 28.11.2021
 */
public class BoundedBlockingQueueImpl implements BoundedBlockingQueue {
	private static BlockingQueue<String> blockingQueue = new LinkedBlockingDeque<>();
	private final Lock lock = new ReentrantLock();
	private final Condition notFull = lock.newCondition();
	private final Condition notEmpty = lock.newCondition();
	private final int limit;

	public BoundedBlockingQueueImpl(int size) {
		if (size <= 1) {
			throw new IllegalStateException("Queue size must be greater than 1...");
		}
		this.limit = size;
	}

	@Override
	public synchronized void add(Object s) throws InterruptedException {
		lock.lock();
		if (s == null) {
			throw new InterruptedException("element should be not empty...");
		}
		try {
			while (blockingQueue.size() >= limit) {
				System.out.println("add (" + s + ") --->bloks,... " + blockingQueue);
				notFull.await();
			}
			blockingQueue.add(String.valueOf(s));
			System.out.println("add (" + s + ") unblocks, " + blockingQueue);
			notEmpty.signal();
		} catch (Exception e) {
			throw new InterruptedException("element can not add... ");
		} finally {
			lock.unlock();
		}
	}

	@Override
	public synchronized Object remove() throws InterruptedException {
		lock.lock();
		try {
			while (blockingQueue.isEmpty()) {
				System.out.println("remove -> bloks,  " + blockingQueue);
				notEmpty.await();
			}
			Object s = blockingQueue.remove();
			System.out.println("remove (" + s + ") unbloks, " + blockingQueue);
			notFull.signal();
			return s;
		} catch (Exception e) {
			throw new InterruptedException("element can not read and remove... ");
		} finally {
			lock.unlock();
		}
	}
}
