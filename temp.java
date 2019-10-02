class DinnerPlates {

    List<Stack<Integer>> stacks = new ArrayList<>();
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    int capacity;
    public DinnerPlates(int capacity) {
        this.capacity = capacity;
    }
    
    public void push(int val) {
        for(int i = 0; i < stacks.size(); i++) {
            Stack stack = stacks.get(i);
            if(stack.size() < capacity) {
                stack.push(val);
                return;
            }
        }
        Stack stack = new Stack();
        stack.push(val);
        stacks.add(stack);
    }
    
    public int pop() {
        if(stacks.size() == 0) {
            return -1;
        }
        Stack<Integer> lastStack = stacks.get(stacks.size() - 1);
        int value = lastStack.pop();
        for(int i = stacks.size() - 1; i >= 0; i--) {
            if(!stacks.get(i).isEmpty()) {
                break;
            } else {
                stacks.remove(stacks.size() - 1);
            }
        }
        return value;
    }
    
    public int popAtStack(int index) {
        if(index < 0 || index > stacks.size() - 1 || stacks.get(index).isEmpty()) {
            return -1;
        }
        Stack<Integer> stack = stacks.get(index);
        int value = stack.pop();
        if(stack.isEmpty() && index == stacks.size() - 1) {
            stacks.remove(index);
        }
        return value;
    }
}
