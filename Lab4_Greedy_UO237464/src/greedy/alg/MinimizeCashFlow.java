package greedy.alg;

import java.util.ArrayList;

public class MinimizeCashFlow {

	private ArrayList<Payment> payments;
	private ArrayList<Payment> solution;
	private ArrayList<Person> people;

	public MinimizeCashFlow() {
		payments = new ArrayList<Payment>();
		people = new ArrayList<Person>();
		solution = new ArrayList<Payment>();
	}

	public void addPayment(Payment payment) {
		// Add payment to payments array
		payments.add(payment);

		// Add target and source to people array(If they aren't in it).
		Person aux1 = new Person(payment.source);
		Person aux2 = new Person(payment.target);
		createPeople(aux1, aux2);
	}

	public int getFinalDebt(String s, String t) {
		if (solution.isEmpty()) {
			return 0;
		} else {
			for (Payment p : solution) {
				if (p.getSource().equals(s)) {
					if (p.getTarget().equals(t)) {
						return p.value;
					}
				}
			}
		}
		return 0;
	}

	public void calculate() {
		Payment aux;
		Person source, target;
		int cond = 1;
		int amount = 0;
		int i, sourcePos, targetPos;

		// Get the general balance of people in the array.
		calculateBalanceInPeople();

		// Create the solution array.
		while (cond != 0) {
			cond = 0;

			// GET MAX AND MIN AMOUNT
			i = 0;
			sourcePos = 0;
			targetPos = 0;
			source = new Person();
			target = new Person();

			for (Person p1 : people) {
				i++;
				if (p1.getBalance() != 0) {
					cond++;
					if (p1.getBalance() < source.getBalance()) {
						source = p1;
						sourcePos = i;
					} else if (p1.getBalance() > target.getBalance()) {
						target = p1;
						targetPos = i;
					}
				}
			}

			// CREATE THE PAYMENT AND ADD IT TO THE SOLUTION ARRAY
			amount = addToSolution(source, target, cond, amount, sourcePos,
					targetPos);
		}
	}

	/**
	 * Method to add a payment to the solution array
	 * 
	 * @param source
	 * @param target
	 * @param cond
	 * @param amount
	 * @param sourcePos
	 * @param targetPos
	 * @return
	 */
	private int addToSolution(Person source, Person target, int cond,
			int amount, int sourcePos, int targetPos) {
		Payment aux;
		if (cond > 0) {

			if (-1 * source.getBalance() > target.getBalance()) {

				amount = target.getBalance();
				people.get(sourcePos - 1).setBalance(
						source.getBalance() + amount);
				people.get(targetPos - 1).setBalance(0);

			} else if (-1 * source.getBalance() < target.getBalance()) {

				amount = -1 * source.getBalance();
				people.get(sourcePos - 1).setBalance(0);
				people.get(targetPos - 1).setBalance(
						target.getBalance() - amount);

			} else if (-1 * source.getBalance() == target.getBalance()) {

				amount = target.getBalance();
				people.get(sourcePos - 1).setBalance(0);
				people.get(targetPos - 1).setBalance(0);

			}

			aux = new Payment(source.getName(), target.getName(), amount);
			solution.add(aux);
		}
		return amount;
	}

	/**
	 * Get the general balance of people in the array.
	 */
	private void calculateBalanceInPeople() {
		for (Payment each : payments) {
			int j = 0;
			if (each != null) {
				while (j != 2) {
					for (Person p : people) {
						if (p != null) {
							// Assign amount to pay or to get of each one.
							if (each.getSource().equals(p.getName())) {
								p.setToPay(each.getValue());
								j++;
							}
							if (each.getTarget().equals(p.getName())) {
								p.setToReceive(each.getValue());
								j++;
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Auxiliary method to create the array of people.
	 * 
	 * @param aux1
	 *            (Person)
	 * @param aux2
	 *            (Person)
	 */
	private void createPeople(Person aux1, Person aux2) {
		boolean cond1 = true, cond2 = true;

		// SOURCE
		for (Person each : people) {
			if (each.name.equals(aux1.name)) {
				cond1 = false;
			}
		}
		if (cond1 == true) {
			people.add(aux1);
		}

		// TARGET
		for (Person each : people) {
			if (each.name.equals(aux2.name)) {
				cond2 = false;
			}
		}

		if (cond2 == true) {
			people.add(aux2);
		}

	}

}
