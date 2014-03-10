/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package adjacencyranking;

/**
 *
 * @author rachelmills
 */
class CategoryTitle {
    
    private String title;
    private int categoryId;
    
    public CategoryTitle(int categoryId, String title) {
        this.categoryId = categoryId;
        this.title = title;
    } 

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the categoryId
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    
    @Override
    public String toString() {
        return "" + categoryId + ":  " + title;
    }
    
}
