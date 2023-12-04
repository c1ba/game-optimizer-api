INSERT INTO gaming_optimizer.components(id, name, type, capacity) VALUES
('cf999b7d-749f-49e4-a449-941ecf046833', 'Intel Core i5-6600', 'processor', 3.3),
('209de56e-bd11-4d7d-96fd-12c509f963a2', 'NVIDIA GeForce GTX 960', 'graphics_card', 4.0),
('b728e570-5021-43aa-9caf-72e78105532a', null, 'ram', 6.0);

INSERT INTO gaming_optimizer.component_combos(id, processor_id, graphics_card_id, ram_id) VALUES
('250c589c-edd3-4a0a-9683-e414050ae1ab', 'cf999b7d-749f-49e4-a449-941ecf046833', '209de56e-bd11-4d7d-96fd-12c509f963a2', 'b728e570-5021-43aa-9caf-72e78105532a');