package com.myant.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import com.myant.model.Contact;
import com.myant.model.Group;
import com.myant.repository.ContactRepository;
import com.myant.repository.GroupRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoDemoApplicationTests {
	@Autowired
	ContactRepository contactRepository;

	@Autowired
	GroupRepository groupRepository;
	
    @Before
    public void setUp() throws Exception {
    	Contact c = new Contact("John", "Lee",
				"Toronto", "6664367218", "324567890", "345987");
    	Contact c1 = new Contact("Jason", "Leen",
				"Ontario", "6664367211", "324567892", "3459827");
    	
    	List<Contact> contactLst = new ArrayList<>(Arrays.asList(c));
    	Group g = new Group("Group1",contactLst);
    	
        //save product, verify has ID value after save
        assertNull(c.getId());
        assertNull(c1.getId());//null before save
        assertNull(g.getId());
        this.contactRepository.save(c);
        this.contactRepository.save(c1);
        this.groupRepository.save(g);
        assertNotNull(c.getId());
        assertNotNull(c1.getId());
        assertNotNull(g.getId());
    }

    
    @Test
    public void testFetchContactData(){
        /*Test data retrieval*/
    	Optional<Contact> c = contactRepository.findByFirstName("John");
        assertNotNull(c.get());
        assertEquals("Lee", c.get().getLastName());
        // fetch by address
        List<Contact> cLst = contactRepository.findByAddress("Ontario");
        assertNotEquals(cLst.size(),0);
        assertEquals("Leen", cLst.get(0).getLastName());
        /*Get all contacts, list should only have two*/
        Iterable<Contact> contacts = contactRepository.findAll();
        int count = 0;
        for(Contact contact : contacts){
            count++;
        }
        assertEquals(count, 2);
    }
    
    @Test
    public void testFetchGroupdata() {
    	Optional<Group> g = groupRepository.findByName("Group1");
    	assertNotNull(g.get());
    	/*Get all contacts, list should only have one*/
        Iterable<Group> groups = groupRepository.findAll();
        int count = 0;
        for(Group group : groups){
            count++;
        }
        assertEquals(count, 1);
    }
    
    @After
    public void tearDown() throws Exception {
      this.contactRepository.deleteAll();
      this.groupRepository.deleteAll();
    }

}
