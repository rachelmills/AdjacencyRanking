package adjacencyranking;

/**
 *
 * @author rachelmills
 */
public class CategoryFrequency implements Comparable<CategoryFrequency> {
    
    private int category;
    private int frequency;

    public CategoryFrequency(int category, int frequency) {
        this.category = category;
        this.frequency = frequency;
    }
    /**
     * @return the category
     */
    public int getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(int category) {
        this.category = category;
    }

    /**
     * @return the frequency
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * @param frequency the frequency to set
     */
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public int compareTo(CategoryFrequency cf) {
        int compare = frequency > cf.getFrequency() ? -1 : frequency < cf.getFrequency() ? 1 : 0;
        if (compare != 0) {
            return compare;
        } 
        return (category > cf.getCategory()) ? 1: (category < cf.getCategory()) ? -1: 0;
    }
}
