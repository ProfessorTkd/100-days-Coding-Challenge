class Solution {
    public ListNode rev(ListNode head){
        if(head==null || head.next==null)
            return head;
        ListNode prev = head;
        ListNode curr = head.next;
        ListNode temp = curr.next;
        while(temp!=null){
            curr.next = prev;
            prev = curr;
            curr = temp;
            temp = temp.next;
        }
        head.next = null;
        curr.next = prev;
        return curr;
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head1 = rev(l1);
        ListNode head2 = rev(l2);
        int carry = 0;
        ListNode head = null;
        ListNode temp = null;
        while (head1 != null || head2 != null || carry != 0) {
            int sum = carry;
            if (head1 != null) {
                sum += head1.val;
                head1 = head1.next;
            }
            if (head2 != null) {
                sum += head2.val;
                head2 = head2.next;
            }
            carry = sum / 10;
            sum = sum % 10;
            ListNode newNode = new ListNode(sum);
            if (head == null) {
                head = newNode;
                temp = head;
            }
            else {
                temp.next = newNode;
                temp = temp.next;
            }
        }
        return rev(head); 
    }
}
