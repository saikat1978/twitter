/* TweetTracker. Copyright (c) Arizona Board of Regents on behalf of Arizona State University
 * @author shamanth
 */
package techathon.core.text.trend;

import java.util.Date;

public class DateInfo implements Comparable
{
    public Date d;
    public int count;

    public int compareTo(Object o) {
        DateInfo temp = (DateInfo) o;
        if(temp.d.after(this.d))
        {
            return -1;
        }
        else
        if(temp.d.before(this.d))
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
}
