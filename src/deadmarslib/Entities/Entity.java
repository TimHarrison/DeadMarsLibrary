package deadmarslib.Entities;

// <editor-fold defaultstate="collapsed" desc="Imports">
import deadmarslib.Game.GameTime;
import deadmarslib.QuadTree.QuadTreeNodeItem;
import deadmarslib.SeparatingAxisTheorem.SatShape;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
// </editor-fold>

/**
 * DeadMarsLib Entity Class
 * 
 * @author Daniel Cecil
 */
public class Entity implements Comparable<Entity> {

    // <editor-fold defaultstate="expanded" desc="Fields">
    
    /**
     * A reference to the {@link EntityManager} this entity belongs to.
     */
    public EntityManager entityManager;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="expanded" desc="Properties">

    QuadTreeNodeItem entQTItemAssociate = null;
    
    Class[] collisionClassFilters;
    EntityCollision[] collisionDefinitions;
    
    boolean entDestroyed = false;
    boolean entCollidable = true;
    boolean entIsColliding = false;
    
    EntityType entType = null;
    
    public SatShape shape;
    
    double entX = 0.0;
    double entY = 0.0;
//    double entWidth = 0.0;
//    double entHeight = 0.0;
//    double entOriginX = 0.0;
//    double entOriginY = 0.0;
    double entDirection = 0.0;
//    double entScale = 1.0;
    double entSpeed = 0.0;
    double entElapsedTime = 0.0;
    double entLifespan = 0.0;
    
    /**
     * Set collision definitions for when this entity collides with another entity.
     * <p>
     * The filters and definitions arrays must be of equal length for the {@link EntityManager} to
     * accept them during collision detection. If they are not of equal length, the {@link EntityManager}
     * will ignore this entity outright and perform no collision detection.
     * 
     * @param filters Array of classes this entity can collide with. (Must inherently
     * be a list subclasses of {@link Entity}.
     * @param definitions Array of {@link EntityCollision} interfaces. Defines what to do in the
     * event of a collision.
     */
    public void setCollisions(Class[] filters, EntityCollision[] definitions) {
        collisionClassFilters = filters;
        collisionDefinitions = definitions;
    }

    /**
     * Sets the {@link QuadTreeNodeItem} to associate with this entity.
     * <p>
     * When an Entity is inserted into an {@link EntityManager} a {@link QuadTreeNodeItem} is automatically
     * assigned to the Entity. It is not necessary or recommended to assign a new {@link QuadTreeNodeItem} to 
     * the Entity unless you are building your own {@link EntityManager}.
     * <p>
     * {@link QuadTreeNodeItem} and {@link Entity} are not synchronized. You must manually update the
     * position, size, etc. of the {@link QuadTreeNodeItem}, whenever the entity is updated, for collision
     * detection and rendering to work properly.
     * 
     * @param i {@link QuadTreeNodeItem} to be associated with this entity.
     */
    public void setQuadTreeNodeItemAssociate(QuadTreeNodeItem i) {
        entQTItemAssociate = i;
    }

    /**
     * Retrieves the {@link QuadTreeNodeItem} associated with this entity if one exists.
     * 
     * @return {@link QuadTreeNodeItem} associated with this entity.
     */
    public QuadTreeNodeItem getQuadTreeNodeItemAssociate() {
        return entQTItemAssociate;
    }

    /**
     * Marks this entity for cleanup and removal from the {@link EntityManager}.
     */
    public void destroy() {
        entDestroyed = true;
    }

    /**
     * Retrieves the destruction status of this entity.
     * 
     * @return whether this entity is marked to be destroyed.
     */
    public boolean isDestroyed() {
        return entDestroyed;
    }

    /**
     * Retrieves whether or not this entity can be collided with.
     * 
     * @return whether or not this entity can be collided with.
     */
    public boolean isCollidable() {
        return entCollidable;
    }

    /**
     * Set whether or not this entity can be collided with.
     * 
     * @param state Can this entity be collided with?
     */
    public void setIsCollidable(boolean state) {
        entCollidable = state;
    }
    
    /**
     * Retrieves whether or not this entity is currently colliding.
     * 
     * @return whether or not this entity is currently colliding.
     */
    public boolean isColliding() {
        return entIsColliding;
    }
    
    /**
     * Sets whether or not this entity is currently colliding.
     * 
     * @param state 
     */
    public void setIsColliding(boolean state) {
        entIsColliding = state;
    }
    
    /**
     * Gets the type of this entity.
     * <p>
     * Can be Actor, Prop, or Background.
     * 
     * @return type of this entity.
     */
    public EntityType getType() {
        return entType;
    }
    
    /**
     * Sets the type of this entity.
     * <p>
     * Available types are Actor, Prop, and Background.
     * 
     * @param type this entity's new type.
     */
    public void setType(EntityType type) {
        entType = type;
    }

    /**
     * Gets the X coordinate of the entity.
     * 
     * @return X coordinate.
     */
    public double getX() {
        return entX;
    }

    /**
     * Sets the X coordinate of the entity.
     * 
     * @param x New X coordinate.
     */
    public void setX(double x) {
        if(x != entX) {
            entX = x;
            shape.move((int)entX, (int)entY);
            if(getQuadTreeNodeItemAssociate() != null) {
                getQuadTreeNodeItemAssociate().setPosition(new Point((int)entX, (int)entY));
            }
        }
    }

    /**
     * Gets the Y coordinate of the entity.
     * 
     * @return Y coordinate.
     */
    public double getY() {
        return entY;
    }

    /**
     * Sets the Y coordinate of the entity.
     * 
     * @param y New Y coordinate.
     */
    public void setY(double y) {
        if(y != entY) {
            entY = y;
            shape.move((int)entX, (int)entY);
            if(getQuadTreeNodeItemAssociate() != null) {
                getQuadTreeNodeItemAssociate().setPosition(new Point((int)entX, (int)entY));
            }
        }
    }
    
    /**
     * Gets the XY coordinates of the entity.
     * 
     * @return XY coordinates.
     */
    public Point getPosition() {
        return new Point((int)entX, (int)entY);
    }

    /**
     * Sets the XY coordinates of the entity.
     * 
     * @param x New X coordinate.
     * @param y New Y coordinate.
     */
    public void setPosition(double x, double y) {
        if(x != entX || y != entY) {
            entX = x;
            entY = y;
            shape.move((int)entX, (int)entY);
            if(getQuadTreeNodeItemAssociate() != null) {
                getQuadTreeNodeItemAssociate().setPosition(new Point((int)entX, (int)entY));
            }
        }
    }
    
    /**
     * Sets the XY coordinates of the entity.
     * 
     * @param pos New XY coordinate.
     */
    public void setPosition(Point pos) {
        if(pos.x != entX || pos.y != entY) {
            entX = pos.x;
            entY = pos.y;
            shape.move((int)entX, (int)entY);
            if(getQuadTreeNodeItemAssociate() != null) {
                getQuadTreeNodeItemAssociate().setPosition(new Point((int)entX, (int)entY));
            }
        }
    }

    /**
     * Gets the width of this entity.
     * 
     * @return Width.
     */
//    public double getWidth() {
//        return entWidth;
//    }
    
    public double getWidth() {
        return shape.getBounds().width;
    }

//    /**
//     * Sets the width of this entity.
//     * 
//     * @param w New width.
//     */
//    public void setWidth(double w) {
//        if(w != entWidth) {
//            entWidth = w;
//            if(getQuadTreeNodeItemAssociate() != null) {
//                getQuadTreeNodeItemAssociate().setSize(new Dimension((int)entWidth, (int)entHeight));
//            }
//        }
//    }

    /**
     * Gets the height of this entity.
     * 
     * @return Height
     */
//    public double getHeight() {
//        return entHeight;
//    }
    
    public double getHeight() {
        return shape.getBounds().height;
    }

//    /**
//     * Sets the Height of this entity.
//     * 
//     * @param h New height.
//     */
//    public void setHeight(double h) {
//        if(h != entHeight) {
//            entHeight = h;
//            if(getQuadTreeNodeItemAssociate() != null) {
//                getQuadTreeNodeItemAssociate().setSize(new Dimension((int)entWidth, (int)entHeight));
//            }
//        }
//    }

    /**
     * Gets the dimensions of this entity.
     * 
     * @return Dimensions.
     */
//    public Dimension getSize() {
//        return new Dimension((int)entWidth, (int)entHeight);
//    }
    
    public Dimension getSize() {
        return new Dimension(shape.getBounds().width, shape.getBounds().height);
    }

//    /**
//     * Sets the dimensions of this entity.
//     * 
//     * @param w New width.
//     * @param h New height.
//     */
//    public void setSize(double w, double h) {
//        if(w != entWidth || h != entHeight) {
//            entWidth = w;
//            entHeight = h;
//            if(getQuadTreeNodeItemAssociate() != null) {
//                getQuadTreeNodeItemAssociate().setSize(new Dimension((int)entWidth, (int)entHeight));
//            }
//        }
//    }
    
//    /**
//     * Sets the dimensions of this entity.
//     * 
//     * @param size New dimensions.
//     */
//    public void setSize(Dimension size) {
//        if(size.width != entWidth || size.height != entHeight) {
//            entWidth = size.width;
//            entHeight = size.height;
//            if(getQuadTreeNodeItemAssociate() != null) {
//                getQuadTreeNodeItemAssociate().setSize(new Dimension((int)entWidth, (int)entHeight));
//            }
//        }
//    }

//    /**
//     * Gets the origin X coordinate of this entity.
//     * <p>
//     * Origin is relative to the entity, not world space.
//     * 
//     * @return Origin X.
//     */
//    public double getOriginX() {
//        return entOriginX;
//    }

//    private void setOriginX(double ox) {
//        entOriginX = ox;
//    }

//    /**
//     * Gets the origin Y coordinate of this entity.
//     * <p>
//     * Origin is relative to the entity, not world space.
//     * 
//     * @return Origin Y.
//     */
//    public double getOriginY() {
//        return entOriginY;
//    }
//
//    private void setOriginY(double oy) {
//        entOriginY = oy;
//    }
//
//    public Point getOriginPosition() {
//        return new Point((int)entOriginX, (int)entOriginY);
//    }
//
//    private void setOriginPosition(double ox, double oy) {
//        entOriginX = ox;
//        entOriginY = oy;
//    }
//    
//    private void setOriginPosition(Point opos) {
//        entOriginX = opos.x;
//        entOriginY = opos.y;
//    }

    /**
     * Gets the direction of this entity.
     * 
     * @return Direction.
     */
    public double getDirection() {
        return entDirection;
    }

    /**
     * Sets the direction of this entity.
     * 
     * @param d New direction.
     */
    public void setDirection(double d) {
        entDirection = d;
    }

//    /**
//     * Gets the scale of this entity.
//     * 
//     * @return Scale.
//     */
//    public double getScale() {
//        return entScale;
//    }
//
//    private void setScale(double s) {
//        entScale = s;
//    }

    /**
     * Gets the speed of this entity.
     * 
     * @return Speed.
     */
    public double getSpeed() {
        return entSpeed;
    }

    /**
     * Sets the speed of this entity.
     * 
     * @param s New speed.
     */
    public void setSpeed(double s) {
        entSpeed = s;
    }

    /**
     * Gets the amount of time this entity has been alive for.
     * 
     * @return time this entity has been alive.
     */
    public double getElapsedTime() {
        return entElapsedTime;
    }

    /**
     * Sets the amount of time this entity has been alive for.
     * <p>
     * Can be used to manipulate the entity's life.
     * 
     * @param et elapsed entity time.
     */
    public void setElapsedTime(double et) {
        entElapsedTime = et;
    }
    
    /**
     * Gets the time this entity is allowed to be alive for.
     * 
     * @return allowed lifetime of this entity.
     */
    public double getLifespan() {
        return entLifespan;
    }

    /**
     * Sets the time this entity is allowed to be alive for.
     * <p>
     * Can be used to set, shorten, or lengthen an entity's lifespan.
     * <p>
     * A value of 0 denotes an unspecified lifetime. The entity will live until you manually destroy it.
     * 
     * @param ls new lifespan to give this entity.
     */
    public void setLifespan(double ls) {
        entLifespan = ls;
    }

    /**
     * Retrieves this entity's bounding rectangle.
     * 
     * @return this entity's bounding rectangle.
     */
//    public Rectangle getBoundingBox() {
//        double scale = this.getScale();
//        int scaledOX = (int) (this.getOriginX() * scale);
//        int scaledOY = (int) (this.getOriginY() * scale);
//        int scaledWidth = (int) (this.getWidth() * scale);
//        int scaledHeight = (int) (this.getHeight() * scale);
//        return new Rectangle((int)(this.getX() - scaledOX), (int)(this.getY() - scaledOY), scaledWidth, scaledHeight);
//    }
    
    public Rectangle getBoundingBox() {
        return shape.getBounds();
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="expanded" desc="Initialization">

    /**
     * Constructor
     * 
     * @param rect Position and Size of new Entity.
     * @param type Type of new Entity.
     */
    public Entity(Rectangle rect, EntityType type) {
        entX = rect.x;
        entY = rect.y;
//        entWidth = rect.width;
//        entHeight = rect.height;
//        entOriginX = entWidth / 2f;
//        entOriginY = entHeight / 2f;
        shape = new SatShape(rect);
        
        entType = type;
        
        switch(type) {
            case Actor:
                this.entCollidable = true;
                break;
            case Prop:
                this.entCollidable = true;
                break;
            case Background:
                this.entCollidable = false;
                break;
            default:
                this.entCollidable = false;
                this.entType = EntityType.Actor;
                break;
        }  
    }

//    public Entity(Rectangle rect, Point point, EntityType type) {
//        entX = rect.x;
//        entY = rect.y;
//        entWidth = rect.width;
//        entHeight = rect.height;
//        entOriginX = point.getX();
//        entOriginY = point.getY();
//        
//        entType = type;
//        
//        switch(type) {
//            case Actor:
//                this.entCollidable = true;
//                break;
//            case Prop:
//                this.entCollidable = true;
//                break;
//            case Background:
//                this.entCollidable = false;
//                break;
//            default:
//                this.entCollidable = false;
//                this.entType = EntityType.Actor;
//                break;
//        }
//    }

    /**
     * Used to initialize an Entity when it is added to an {@link EntityManager}.
     * <p>
     * Called as soon as an entity is added to an {@link EntityManager}.
     */
    public void Initialize() {
    }
    
    @Override
    public int compareTo(Entity o) {
        return 0;
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="expanded" desc="Load and Unload">

    /**
     * Overridable. Used to load content in an Entity.
     * <p>
     * Called as soon as an entity is added to an {@link EntityManager}.
     * <p>
     * Essentially the same as Initialize().
     */
    public void LoadContent() {
    }

    /**
     * Overridable. Used to clean up Entity content.
     * <p>
     * Called when an entity is being removed from an {@link EntityManager}.
     */
    public void UnloadContent() {
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="expanded" desc="Update and Render">

    /**
     * Overridable. Used to update Entity logic.
     * 
     * @param gameTime reference to the current {@link GameTime}.
     */
    public void Update(GameTime gameTime) {
        entElapsedTime += gameTime.elapsedGameTime.getMilliseconds();
    }

    /**
     * Overridable. Used to render an Entity to the game screen.
     * 
     * @param gameTime reference to the current {@link GameTime}.
     * @param g reference to the graphics context for drawing.
     */
    public void Render(GameTime gameTime, Graphics g) {
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="expanded" desc="Collision">

    /**
     * Creates a collision event between this Entity and the specified Entity.
     * <p>
     * Event is only created if the specified Entity exists in THIS Entity's collision definitions.
     * 
     * @param ent Entity to collide with.
     */
    public void Collision(Entity ent) {
        int onColIndex = 0;
        
        if(collisionClassFilters != null && collisionDefinitions != null && collisionClassFilters.length == collisionDefinitions.length) {
        
            for (Class clazz : collisionClassFilters) {
                if (ent.getClass() == clazz) {
                    collisionDefinitions[onColIndex].onCollision(this, ent);
                    return;
                }
                onColIndex++;
            }
        }
    }
    
    // </editor-fold>
}
