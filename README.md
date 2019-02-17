# JSONQuery JPA

JSONQuery JPA translates JSON queries to JPA queries. Using [QueryDSL] (https://github.com/mysema/querydsl) each 
translated query is type-safe and fluent . JSON query syntax is based on the popular [jqGrid] (http://www.trirand.com/blog/) 
plugin for jQuery.

Its main purpose is to provide easy wrapper for JavaScript plugins and widgets that query JPA-based backend data layer with Play framework  [JpaApi] (https://www.playframework.com/documentation/2.5.1/api/java/play/db/jpa/JPAApi.html). The JpaApi simply provides an injected enitymanager, this can be replaced with another 
solution depending on the framework is meant to be deployed.  


Here's a sample JSON query: 

    "{"groupOp":"AND","rules":" +
      "[{"field":"id","op":"eq","data":"1"}," +
    	"{"field":"name","op":"bw","data":"Jane Adams"}," +
    	"{"field":"age","op":"gt","data":"20"}," +
    	"{"field":"money","op":"lt","data":"2000.75"}," +
    	"{"field":"birthDate","op":"eq","data":"1959-09-30T00:00:00.000Z"}," +
    	"{"field":"parent.id","op":"eq","data":"2"}," +
    	"{"field":"creationDate","op":"eq","data":"01-31-1980 +5"}" +
    	"]}";
    	
Note: The framework is designed only for reading data. It doesn't create, update, or delete.


# Getting Started

1. Dowload or clone from Git and then use Maven:

    $ git clone ...
    $ mvn install

2. Use Google Guice to bind the fliter service (you can use any other dependency mangement tool)
	"bind(IFilterService.class).to(FilterService.class);"

3. Inject the filter service 
	@Inject
    IFilterService filterService;

4. Search the entity class
	


		public JqgridResponse searchAppUsers(String token, Boolean search, String filters, Integer page, Integer rows, String sidx, String sord)  {        
            Order order = new Order(JPAAppUser.class);
            // Prepare arguments before reading from service
            Pageable pageable = new PageRequest(page - 1, rows);
            
            OrderSpecifier<?> orderSpecifier = order.by(sidx, OrderEnum.getEnum(sord));
            BooleanBuilder booleanBuilder = service.getJsonBooleanBuilder(JPAAppUser.class).build(new JsonFilter(filters));

            // Add extra filters manually if necessary
            // Read from service
            Page<JPAAppUser> results = service.readAndCount(entityManager,booleanBuilder, pageable, JPAAppUser.class, orderSpecifier);

            // Map response
            JqgridResponse<AppUser> response = new JqgridResponse<AppUser>();
            response.setRows(results.getContent().stream().map(JPAAppUser::AppUserFromJPAAppUser).collect(Collectors.toList()));
            response.setRecords(Long.valueOf(results.getTotalElements()).toString());
            response.setTotal(Integer.valueOf(results.getTotalPages()).toString());
            response.setPage(Integer.valueOf(results.getNumber() + 1).toString());

            return response;
    	}

#TODO
1. Create samples
2. Write Tests
