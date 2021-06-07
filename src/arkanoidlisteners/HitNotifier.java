// 315240937
// Michael Saar

package arkanoidlisteners;


/**
 * Classes that implement this interface are objects that can be hit by other Collidable object.
 */
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl hitListener to add to HitListeners list of hittable object.
     */
    void addHitListener(HitListener hl);

    /**
     * remove hl from object's list.
     *
     * @param hl hitListener to remove from HitListeners list of hittable object.
     */
    void removeHitListener(HitListener hl);
}
