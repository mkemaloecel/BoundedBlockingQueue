/**
 * @author kemal
 * @since 28.11.2021
 */
public interface BoundedBlockingQueue<E> {
	void add(E s) throws InterruptedException;
	E remove() throws InterruptedException;
}
