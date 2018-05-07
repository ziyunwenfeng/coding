
class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
         val = x;
         next = null;
      }
  }
 
class Solution {
    ListNode insertionSortList(ListNode head) {
        ListNode head2 = new ListNode(head.val);
        if(head==null||head.next==null)
        	head2 = head;
        ListNode tmp = head;
        ListNode p ;
        while(tmp!=null){
        	
        		p = head2;
        		while(p.next!=null&&p.val<tmp.val){
        			p = p.next;
        		}
        		tmp.next = p.next;
        		p.next = tmp;
        	tmp = tmp.next;
        }
        return head2;
    }
}
public class R {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1 = "helloworld";
		String s2 = new String("helloworld");
		String s3 = "hello"+"world";
		System.out.println(s1==s2);
		System.out.println(s1==s3);
		System.out.println(s1==s1.intern());
	}

}
